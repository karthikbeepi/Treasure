(ns treasure.ns)
(require '[clojure.string :as str]) ; for string function

(def oneString "") ; already defined as a string 
(def noOfRows 0)

;Method for counting noOfCol
(defn findRC [file]
  (with-open [rdr (clojure.java.io/reader file)]
    (doseq [line (line-seq rdr)]
      (def noOfCol (count line)))))
;Method for reading from file character by character
(defn readFile [file]
  (findRC file)
  (with-open [rdr (clojure.java.io/reader file)]
    (doseq [line (line-seq rdr)]
      (def oneString (concat oneString line))
      (def noOfRows (+ noOfRows 1))
      )))

;Program begins from here
(readFile "map1.txt")
(println "NoR :" noOfRows "NoC: " noOfCol)
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


; (println (checkBoundary -1 ))
; Find the path to @
(defn findPath [ i ]
  (def res (atom false))
  false
  (if (checkBoundary i)
    false
    (if (= (str (aget arr i)) "@")
      true
      (do
        (aset arr i "!")
        ; (def up (- i noOfCol))
        ; (def down (+ i noOfCol))
        ; (def right (getRight i))
        ; (def left (getLeft i))
        (def upRes (findPath (getUp i)))
        (def leftRes (findPath (getLeft i)))
        (def rightRes (findPath (getRight i)))
        (def downRes (findPath (getDown i)))
        ; (def res (or upRes downRes leftRes rightRes))
        (println "Came here" i upRes leftRes downRes rightRes res)
        ; (println (findPath 1))
        (if (or upRes downRes leftRes rightRes)
          (do
            (aset arr i "+")
            true
            ))
        false)
      )
    )
  )
(println (findPath 0))
(println (apply str arr))

