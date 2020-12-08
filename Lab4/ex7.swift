import Dispatch

func bubbleSort(_ arr: inout Array<Int>) {
  for i in 0..<arr.count {
    for j in 0..<arr.count - i - 1 {
      if(arr[j] > arr[j+1]) {
        arr.swapAt(j, j+1)
      }
    }
  }
}

func selectionSort(_ arr: inout Array<Int>) {
  var minIndex: Int

  for i in 0..<arr.count - 1 {
    minIndex = i

    for j in i+1..<arr.count {
      if(arr[j] < arr[minIndex]) {
        minIndex = j
      }
    }
    
    arr.swapAt(minIndex, i)
  }
}

func insertionSort(_ arr: inout Array<Int>) {
  var key: Int
  var j: Int

  for i in 1..<arr.count {
    key = arr[i]
    j = i - 1

    while (j >= 0 && arr[j] > key) {
      arr[j+1] = arr[j];  
      j -= 1;
    }

    arr[j+1] = key
  }
}

// var numArray = [1, 5, 4, 2, 3]
// print(numArray)
// bubbleSort(&numArray)
// print(numArray)

// numArray = [1, 5, 4, 2, 3]
// print(numArray)
// selectionSort(&numArray)
// print(numArray)

// numArray = [1, 5, 4, 2, 3]
// print(numArray)
// insertionSort(&numArray)
// print(numArray)

func runSort(_ mySort: (inout Array<Int>) -> (), _ arr: inout Array<Int>) -> (Int, Int, Int, Double) {
  let arrSum: Int = arr.reduce(0, +)
  let arrMin: Int = arr.min()!
  let arrMax: Int = arr.max()!
  let arrAvg: Double = Double(arrSum) / Double(arr.count)
  mySort(&arr)

  return (arrSum, arrMin, arrMax, arrAvg)
}

// var numArray = [1, 5, 4, 2, 3]
// print("Initial array: ", numArray)
// let (arrSum, arrMin, arrMax, arrAvg) = runSort(bubbleSort, &numArray)
// print("Sum: ", arrSum)
// print("Min: ", arrMin)
// print("Max: ", arrMax)
// print("Avg: ", arrAvg)
// print("Sorted array: ", numArray)

print("Provide array size:")
var arraySize = readLine()
print("Provide max array value:")
var arrayMax = readLine()

var numArray: Array<Int> = []

if arraySize != nil || arrayMax != nil {
  let arrayIntSize = Int(arraySize!)!
  let arrayIntMax = Int(arrayMax!)!
  for _ in 0..<arrayIntSize {
    numArray.append(Int.random(in: 0..<arrayIntMax))
  }
}

var numArrayCopy = numArray.map { $0 }
var start = DispatchTime.now()
bubbleSort(&numArrayCopy)
var end = DispatchTime.now()
var nanoTime = end.uptimeNanoseconds - start.uptimeNanoseconds
print("Bubble sort - took: ", Double(nanoTime) / 1_000_000_000, " seconds")


numArrayCopy = numArray.map { $0 }
start = DispatchTime.now()
selectionSort(&numArrayCopy)
end = DispatchTime.now()
nanoTime = end.uptimeNanoseconds - start.uptimeNanoseconds
print("Selection sort - took: ", Double(nanoTime) / 1_000_000_000, " seconds")

numArrayCopy = numArray.map { $0 }
start = DispatchTime.now()
insertionSort(&numArrayCopy)
end = DispatchTime.now()
nanoTime = end.uptimeNanoseconds - start.uptimeNanoseconds
print("Insertion sort - took: ", Double(nanoTime) / 1_000_000_000, " seconds")