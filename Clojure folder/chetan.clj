(require '[clojure.string :as str])
(defn read-in-board [filepath]
  (str/split-lines (slurp filepath)))
(let [board (read-in-board "map.txt")]
  (str (get-in board [1 1])))

(def a (mapv vec (read-in-board "map.txt")))
(println (nth (nth a 3) 3))

(defn h [i]
  (if (= i 0)
    (println "HI")
    (do 
      (h (dec i))
      (println "He"))))

(h 10)

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