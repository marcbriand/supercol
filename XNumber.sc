XNumber {
  var num;
  *new {
    arg win, left, top, width, font, initvalue;
    var tempnum;
    tempnum = StaticText.new(win, Rect(left, top, width, width/3.2));
    tempnum.background = Color.yellow(0.8);
    tempnum.alpha = 0.9;
    tempnum.align = \center;
    tempnum.string = initvalue;
    tempnum.font = font;

    ^super.newCopyArgs(tempnum);
  }

  getValue {
	^num.string;
  }

  setValue_ {
    arg n;
    num.string = n.round;
  }
}

