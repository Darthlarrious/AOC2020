(ns days.day5
  (:require [clojure.string :as s]))

(def day5Input (s/split-lines (slurp "inputs/day5.txt")))

(defn findRow [current next]
  (if (= \F next)
    {:min (:min current) :max (- (:max current) (int (/ (- (inc (:max current)) (:min current)) 2)))}
    {:min (+ (:min current) (int (/ (- (inc (:max current)) (:min current)) 2)))  :max (:max current)}
    ))
(defn findSeat [current next]
  (if (= \L next)
    {:min (:min current) :max (- (:max current) (int (/ (- (inc (:max current)) (:min current)) 2)))}
    {:min (+ (:min current) (int (/ (- (inc (:max current)) (:min current)) 2)))  :max (:max current)}
    ))

(defn seatId [seat]
  (+
    (* 8 (:row seat))
    (:seat seat)))
(defn find-seat-id
  [pass]
  (let [rowNum (:max (reduce findRow '{:min 0 :max 127} (take 8 pass)))
        seatNum (:max (reduce findSeat '{:min 0 :max 7} (take-last 3 pass)))]
      {:row rowNum :seat seatNum :id (seatId {:row rowNum :seat seatNum})}
    )
  )

(defn day5Part1 [] (reduce max (map #(:id (find-seat-id %)) day5Input)))