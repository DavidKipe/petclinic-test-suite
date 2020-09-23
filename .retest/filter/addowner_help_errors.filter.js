
function matches(element, diff) {
	if (diff == null || diff.key == null || diff.expected == null || diff.actual == null)
		return false;

	if (diff.key == "text") {
	    var actual = String(diff.actual);
	    var expected = String(diff.expected);
	    return (
	        (actual == "must not be empty" && (expected == "may not be empty" || expected == "numeric value out of bounds (<10 digits>.<0 digits> expected)"))
            ||
            (actual == "numeric value out of bounds (<10 digits>.<0 digits> expected)" && expected == "must not be empty")
            ||
            (actual == "may not be empty" && expected == "must not be empty")
        );
	}

	return false;
}
