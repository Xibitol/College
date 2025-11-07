import base.test as test
import base

tests = [
	"Exercise 1",
	test.TestFunc(base.f2c, [
		test.TestCase("f2c Zero", (0,), (79 + 81)/9),
		test.TestCase("f2c 1.8", (148,), 100)
	]),
	test.TestFunc(base.c2f, [
		test.TestCase("c2f Zero", (0,), 32),
		test.TestCase("c2f 1/1.8", (1/1.8,), 33)
	]),
	"Exercise 2",
	test.TestFunc(base.fiboIter, [
		test.TestCase("fiboIter Zero", (0,), 0),
		test.TestCase("fiboIter 1", (1,), 1),
		test.TestCase("fiboIter 2", (2,), 1),
		test.TestCase("fiboIter 10", (10,), 55)
	]),
	test.TestFunc(base.fiboRec, [
		test.TestCase("fiboRec Zero", (0,), 0),
		test.TestCase("fiboRec 1", (1,), 1),
		test.TestCase("fiboRec 2", (2,), 1),
		test.TestCase("fiboRec 10", (10,), 55)
	]),
	"Exercise 3",
	test.TestFunc(base.singTTDC, [
		test.TestCase("singTTDC Zero", ([],), "")
	]),

]

for t in tests:
	if type(t) == str:
		print("#", t)
	else:
		t.exec()

print(base.singTTDC([
	"partridge in a pear tree",
	"turtle doves",
	"French hens",
	"calling birds",
	"gold rings",
	"geese a-laying",
	"swans a-swimming",
	"maids a-milking",
	"ladies dancing",
	"lords a-leaping",
	"pipers piping",
	"drummers drumming"
], True))