XGrowWindow {
  var cinfos, comps;
  *new {
    var tcinfos = List.new(10);
    var tcomps = List.new(10);
    ^super.newCopyArgs(tcinfos, tcomps);
  }

  addXKnob {
    arg ll, ul, width, mode, font, defval, lab, knobfunc = {arg a;};
    var info = List["XKnob", ll, ul, width, mode, font, defval, lab, knobfunc];
    cinfos.add(info)
  }

  minOf {
    arg a, b;
    if (a < b, {^a;}, {^b;})
  }

  maxOf {
     arg a, b;
     if (a > b, {^a;}, {^b;})
  }

  getComp {
    arg i;
    ^comps.at(i);
  }

  createWin {
    arg name, winleft, wintop, color, alpha, s;
    var xmin, xmax, ymin, ymax, win, leftstart = 0;
    xmin = 1000000;
    xmax = 0;
    ymin =  1000000;
    ymax = 0;
    cinfos.do({
      arg item, i;
      var ct = item.at(0);
      if (ct == "XKnob",
        {
		  var left, top, right, bottom, width;
					"xmax pre = ".post; xmax.postln;
		  left = xmax + 10;
		  width = item.at(3);
		  right = left + width;
		  xmax = right + 10;

					"left = ".post; left.postln;
					"width = ".post; width.postln;
					"right = ".post; right.postln;
					"xmax post = ".post; xmax.postln;

		  bottom = width + (width/(3.2*2.5) + (width/8));
		  ymax = this.maxOf(ymax, bottom);
        }
      )
    });

		"creating window, winleft = ".post; winleft.post; ", xmax = ".post; xmax.postln;
	win = Window.new(name, Rect(winleft, wintop, xmax, (ymax + 1 + 20)));
    win.onClose = {s.freeAll; Window.closeAll; "Window closed".postln; "".postln};
    win.background = color;
    win.alpha = alpha;
    FreqScope.new;
    win.front;

    cinfos.do({
        arg item, i;
        var ct = item.at(0); xmax = 0;
        if (ct == "XKnob",
          {
            var xknob, func;
			xknob = XKnob.new(win, item.at(1), item.at(2), leftstart + 10, 10, item.at(3), item.at(4), item.at(5), item.at(6), item.at(7));
			leftstart = leftstart + item.at(3) + 20; //  was 10
            xknob.action = {arg v;
              var mapped = xknob.map(v.value);
              xknob.number = mapped;
						item.at(7).post; ": ".post; mapped.postln;
              func = item.at(8);
              func.value(mapped);
            };

            comps.add(xknob);
          }
        )
    });

    ^win;
  }
}
