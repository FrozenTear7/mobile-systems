let N = 10

func fib(x: Int) -> Int {
  guard x > 1 else { return x }
  return fib(x: x - 1) + fib(x: x - 2)
}

for i in 1..<N {
  print(i, " - ", fib(x: i))
}