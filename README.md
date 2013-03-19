Modality-toolkit
================

Modality is a toolkit created by a loose collaboration between both developers and (advanced) users of SuperCollider (also called Modality, see below).

One of its basic ideas is to simplify the creation of your very personal instruments with SuperCollider, using controllers of many different kinds. To this end, a common code interface, MKtl, is used for connecting  controllers from different sources (and protocols) like HID and MIDI, Serial, OSC, GUI, etc.

A second starting point is that the same physical interfaces (sets of sliders, buttons, motion sensors etc etc) can be used for many different purposes, and a highly modal approach to mapping and on-the-fly remapping can help to make a setup much more flexible, powerful, and interesting to play. For example, when improvising with acoustic musicians, highly modal interfaces allow much faster changes of overall direction.

http://modality.bek.no/

The Modality project was initiated by Jeff Carey and Bjoernar Habbestad, and collaborators so far have included: Marije Baalman, Alberto de Campo, Wouter Snoei, Till Bovermann, Miguel Negrao, Robert van Heumen, and Hannes Hoelzl.


Acknowledgements:
Modality and its research meetings have kindly been supported by BEK in Bergen, Norway, and STEIM, Amsterdam.

Installation
============

Drag this folder to the SuperCollider extensions folder

To use the functional reactive interface (FRP) to the toolkit first install the FP-Lib:
https://github.com/miguel-negrao/FPLib

Documentation
===============

Getting started:

See the "Modality Lib" reference file in sc help.

Writting a Ktl description for an unsupported controller:

MKtlSpecs/_HowToMakeAKtlDescription_.scd

FRP examples:

modality/Examples/FRP/Modality FRP tutorial.scd




