func bubbleSort(_ arr: inout Array<Int>) {
  print(arr)
  for i in 0..<arr.count {
    for j in 0..<arr.count - i - 1 {
      if(arr[j] > arr[j+1]) {
        arr.swapAt(j, j+1)
      }
    }
  }
}

func bubbleSort(_ arr: inout Array<Int>) {
  print(arr)
  for i in 0..<arr.count {
    for j in 0..<arr.count - i - 1 {
      if(arr[j] > arr[j+1]) {
        arr.swapAt(j, j+1)
      }
    }
  }
}

func bubbleSort(_ arr: inout Array<Int>) {
  print(arr)
  for i in 0..<arr.count {
    for j in 0..<arr.count - i - 1 {
      if(arr[j] > arr[j+1]) {
        arr.swapAt(j, j+1)
      }
    }
  }
}

var numArray = [1, 5, 4, 2, 3]
bubbleSort(&numArray)
print(numArray)

numArray = [1, 5, 4, 2, 3]
bubbleSort(&numArray)
print(numArray)

numArray = [1, 5, 4, 2, 3]
bubbleSort(&numArray)
print(numArray)