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
(readFile "map.txt")
(println "NoR :" noOfRows "NoC: " noOfCol)
(def oneString (apply str oneString))
(println oneString)
(def arr (to-array oneString)) ;Made into an array
; to access a particular value in the array (aget arr 0)
; to change a particular value in the array (aset arr 0 "!")
; to print the whole array (println (apply str arr))


; (def input_map (slurp "map.txt"))
; (def oneString (str/split input_map  #"\n"))
; (println oneString)
; (println (str (.replaceAll oneString "-" "!")))
; (defn [sb (String. input_map)]
;   (println (str (.replace sb "-" "!"))))
; (def split_map (str/split input_map #"\n"))
; Access 0th row 3 col (println (nth (nth split_map 0) 3))
; (def cnt_rows (count split_map))
; (def single_arr (to-array-2d [(subvec split_map 0 3)]))
; (aset single_arr 0 1 "k")
; (println (alength  single_arr))
; (println (aget single_arr 0 1))