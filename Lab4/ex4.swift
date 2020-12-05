let N = 8

func getTreeLine(x: Int) -> Void {
  var result = ""

  for _ in 0..<x {
    result += " "
  }

  for _ in 0..<2*(N-x)-1 {
    result += "a"
  }

  for _ in 0..<x {
    result += " "
  }

  print(result)
}

for i in 0..<N {
  getTreeLine(x: i)
}