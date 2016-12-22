XKnob {
  var knob, num, label, controlSpec;
  *new {
    arg win, ll, ul, left, top, width, mode, font, defvalue, lab;
    var tempknob, tempnum, templabel, tempspec;

    tempspec = ControlSpec(ll, ul, mode, 0, defvalue, lab);
    tempknob = Knob.new(win, Rect(left, top, width, width));
    tempknob.value = tempspec.unmap(tempspec.default);

    tempnum = XNumber(win, (left + (width/2) - (width/5)), width+10, width/2.5, font, defvalue);
    templabel = XLabel(win, left, width + 40, width, font, lab);

    ^super.newCopyArgs(tempknob, tempnum, templabel, tempspec);
  }

  action_ {
      arg a;
      knob.action = a;
  }

  value_ {
      arg v;
      knob.value = v;
  }

  map {
    arg v;
    ^controlSpec.map(v);
  }

  number {
    var strval;
	strval = num.getValue();
	^strval.asInteger;
  }

  number_ {
    arg n;
    num.setValue_(n);
  }
}