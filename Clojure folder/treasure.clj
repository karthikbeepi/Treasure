(ns treasure.ns)
(require '[clojure.string :as str]) ; for string function

(def oneString "") ; already defined as a string
(def noOfRows 0)

;Method for counting noOfCol
(defn findRC [file]
  (with-open [rdr (clojure.java.io/reader file)]
    (doseq [line (line-seq rdr)]
      (def noOfCol (count line))
      
      )))
;Method for reading from file character by character
(defn readFile [file]
  (findRC file)
  (with-open [rdr (clojure.java.io/reader file)]
    (doseq [line (line-seq rdr)]
      (def oneString (concat oneString line))
      (def noOfRows (+ noOfRows 1))
      )))

;Program begins from here
(readFile "map.txt")
; (println "NoR :" noOfRows "NoC: " noOfCol)
(def oneString (apply str oneString))
; (println oneString)
(def arr (to-array oneString)) ;Made into an array
; to access a particular value in the array (aget arr 0)
; to change a particular value in the array (aset arr 0 "!")
; to print the whole array (println (apply str arr))

;Method to get left neighbour
(defn getLeft [i]
  (def truthVal (rem i noOfCol))
  (if (= truthVal 0) -1 (- i 1))
)

;Method to get right neighbour
(defn getRight [i]
  (def truthVal (rem (+ i 1) noOfCol))
  (if (= truthVal 0) -1 (+ i 1))
)

;Method to get up neighbour
(defn getUp [i]
  (- i noOfCol)
  )

;Method to get down neighbour
(defn getDown [i]
  (+ i noOfCol))

;Method to check Boundary Condition
(defn checkBoundary [i]
  (if (or (< i 0)
          (>= i (* noOfCol noOfRows))
          (= (str (aget arr i)) "#")
          (= (str (aget arr i)) "!")
          )
    true
    false
    )
 )

(defn checkReached [i]
  (if (checkBoundary i)
    false
    (if (= (str (aget arr i)) "@")
      true
      false))
  )


(defn neighbourReached [i]
  (if (checkBoundary i)
    (do
      ; (println "Out of Reach" i)
      false)
    (if (checkReached (getDown i))
      (do
        ; (println "Reached " i "Down")
        true)
      (if (checkReached (getRight i))
        (do
          ; (println "Reached " i "Right")
          true)
        (if (checkReached (getLeft i))
          (do
            ; (println "Reached " i "Left")
            true)
          (if (checkReached (getUp i))
            (do
              ; (println "Reached " i "Up")
              true)
            (if (checkReached i)
              (do
                ; (println "Reached " i)
                true)
              false
              )
            )
          )
        )))
  )

;  (println (checkReached 8))
;  (println (neighbourReached 0))

(defn searchPath [i]
  (if (checkBoundary i)
    false
    (if (checkReached i)
      true
      (do  (aset arr i "!")
(if (neighbourReached i)
  (do
    (aset arr i "+")
    true)
  (if (searchPath (getDown i))
    (do
      (aset arr i "+")
      true)
    (if (searchPath (getLeft i))
      (do
        (aset arr i "+")
        true)
      (if (searchPath (getUp i))
        (do
          (aset arr i "+")
          true)
        (if (searchPath (getRight i))
          (do
            (aset arr i "+")
            true)
          false))))) )
      )
    )
  )
(defn printRes []
  (def example_var 0)
  (doseq [n arr]
    (print n)
    (def example_var (inc example_var))
    (if (= (rem example_var noOfCol) 0)
      (println))))
(println "This is my challenge:")
(printRes)
(if (searchPath 0)
  (println "Woo hoo, I found the treasure :-) phew!")
  (println "Uh oh, I could not find the treasure :'( boo")
  )
; (def finalStr (apply str arr))

(printRes)
; (println (clojure-version))