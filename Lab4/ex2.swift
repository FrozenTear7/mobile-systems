print("""
  1 - add a and b
  2 - subtract a from b
  3 - multiply a by b
  4 - divine a by b
  5 - a to the power of b
  6 - factorial of a
  q - exit the program\n
""")

func add(a: Double, b: Double) -> Double {
  return a + b
}

func sub(a: Double, b: Double) -> Double {
  return a - b
}

func mul(a: Double, b: Double) -> Double {
  return a * b
}

func div(a: Double, b: Double) -> Double {
  return a / b
}

func power(a: Double, b: Int) -> Double {
  var result = a

  // for _ in 1 ..< b {
  //   result *= a
  // }

  // var i = 1
  // while i < b {
  //   result *= a
  //   i += 1
  // }

  var i = 1
  repeat {
    result *= a
    i += 1
  } while i < b

  return result
}

func factRecursive(a: Int) -> Int {
  if a == 1 {
    return 1
  } else {
    return a * factRecursive(a: a - 1)
  }
}

func fact(a: Int) -> Int {
  var result: Int = 1

  for i in 1 ..< a+1 {
    result *= i
  }

  return result
}

func runCalculator(option: String) -> Void {
  var inputA: String?
  var inputB: String?
  var doubleInputA: Double?
  var doubleInputB: Double?
  var intInputA: Int?

  switch option {
    case "1", "2", "3", "4":
      print("A: ")
      inputA = readLine()
      print("B: ")
      inputB = readLine()

      if inputA != nil || inputB != nil {
        doubleInputA = Double(inputA!)
        doubleInputB = Double(inputB!)
      }
    case "5":
      print("A: ")
      inputA = readLine()
      print("B: ")
      inputB = readLine()

      if inputA != nil || inputB != nil {
        doubleInputA = Double(inputA!)
        intInputA = Int(inputB!)
      }
    case "6":
      print("A: ")
      inputA = readLine()

      if inputA != nil {
        intInputA = Int(inputA!)
      }
    default:
      print("Provide a valid option")
      return
  }

  switch option {
    case "1":
      print("Add result:", add(a: doubleInputA!, b: doubleInputB!))
    case "2":
      print("Subtract result:", sub(a: doubleInputA!, b: doubleInputB!))
    case "3":
      print("Multiply result:", mul(a: doubleInputA!, b: doubleInputB!))
    case "4":
      print("Divide result:", div(a: doubleInputA!, b: doubleInputB!))
    case "5":
      print("Power result:", power(a: doubleInputA!, b: intInputA!))
    case "6":
      print("Factorial result:", factRecursive(a: intInputA!))
    default:
      print("Provide a valid option")
  }
}

while true {
  print("\nWhich operation to perform:")
  let optionInput: String? = readLine()

  if optionInput == "q" {
    break
  } else {
    runCalculator(option: optionInput!)
  }
}
