MIDIAnalysis {
	classvar elements, respTypes, elemDictByType, results;
	*analyse { |rawCapture|
		respTypes = [];
		elements = rawCapture.clump(2).collect {
			|pair| pair[1].put(\rawElName, pair[0])
		};

		elemDictByType = ();

		"*** MIDICapture analysis: ***".postln;
		MIDIMKtl.allMsgTypes.collect { |type|
			var matchingEls = elements.select { |el| el[\midiType] == type };
			[ type, matchingEls.size].postln;
			if (matchingEls.size > 0) {
				respTypes = respTypes.add(type);
				elemDictByType.put(type, matchingEls)
			};
		};

		results = [
			rawElCount: elements.size,
			respTypes: respTypes];

		if (respTypes.includesAny([\noteOn, \noteOff])) {
			this.analyseNoteEls;
		};
		if (respTypes.includes(\touch)) {
			this.analyseTouch;

		};
		if (respTypes.includes(\cc)) {
			this.analyseCC;
		};
		if (respTypes.includes(\bend)) {
			this.analyseBend;
		};

		^results
	}

	*analyseNoteEls {
		var noteOnEls =elemDictByType[\noteOn];
		var noteOffEls =elemDictByType[\noteOff];
		var noteOnChan, noteOffChan;
		var noteOnNotes, noteOffNotes;
		"noteOnEls, noteOffEls:".postln;

		noteOnEls.postcs;
		noteOffEls.postcs;

		noteOnChan = this.compressInfo(noteOnEls, \chan);
		noteOffChan = this.compressInfo(noteOffEls, \chan);
		"".postln;

		if (noteOnChan.isKindOf(SimpleNumber)) {
			"noteOn uses single channel on notes: ".post;
			noteOnNotes = this.compressInfo(noteOnEls, \midiNote);
			noteOnNotes.postln;
		};

		if (noteOffChan.isKindOf(SimpleNumber)) {
			"noteOff uses single channel on notes: ".post;
			noteOffNotes = this.compressInfo(noteOffEls, \midiNote);
			noteOffNotes.postln;
		};
results = results ++ [
			noteOn: (channel: noteOnChan),
			noteOff: (channel: noteOffChan)
		];

	}

	*compressInfo { |dict, key|
		^dict.collectAs(_[key], Array).asSet.asArray.sort.unbubble;
	}
	// too tired to figure this out now.. later
	reduceToConsecutive {|array|

	}

	*checkMsgTypes { |devDesc|
		var types = Set.new;
		devDesc.collect { |el, i| if (i.odd) { types.add(el.midiType) } };
		^types.asArray;
	}

	*checkTouch { |devDesc|
		var touchEls = devDesc.select { |el, i| (i.odd and: { el.midiType == \cc }) };
		var touchChan =  this.compressInfo(touchEls, \chan);
		^(channel: touchChan, numEls: touchEls.size);
	}

	// not tested yet
	*analyseBend {
		var bendEls =elemDictByType[\bend];
		var bendChan =  this.compressInfo(bendEls, \chan);

		"bendEls:".postln; bendEls.postcs;
		("bendChan: " + bendChan).postln;
		"".postln;
		results = results ++ [
			bend: (channel: bendChan)
		];
	}

	*analyseCC {
		var ccEls =elemDictByType[\cc];
		var ccChan = this.compressInfo(ccEls, \chan);
		var ccnums;
		"ccEls:".postln;
		ccEls.postcs;
		"".postln;

		if (ccChan.isKindOf(SimpleNumber)) {
			"cc uses single channel on ccnums: ".post;
			ccnums = this.compressInfo(ccEls, \midiNote);
			ccnums.postln;
		};
	}
}