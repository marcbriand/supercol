XLabel {
    var label;
    *new {
        arg win, left, top, width, font, val;
        var templabel;
        templabel = StaticText.new(win, Rect(left, top, width, width/8));
        templabel.string = val;
        templabel.align = \center;
        templabel.font = font;

        ^super.newCopyArgs(templabel);
    }
}
