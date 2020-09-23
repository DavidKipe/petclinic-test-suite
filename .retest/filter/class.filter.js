
function matches(element, diff) {
	if (diff == null || diff.key == null || diff.expected == null || diff.actual == null)
		return false;

	if (diff.key == "class") {
		var actual = String(diff.actual);
		var expected = String(diff.expected);

		actual = actual.replace(/\s+/g, '');
		expected = expected.replace(/\s+/g, '');

		return actual == expected;
	}

	return false;
}
