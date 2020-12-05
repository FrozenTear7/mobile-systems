let testStr = "test string"

var result = ""

for char in testStr {
  result = "\(char)" + result
}

print(result)