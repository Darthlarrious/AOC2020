(ns days.day7
  (:require [clojure.string :as s]))

(def day7Input (map #(s/replace % #"\." "") (s/split-lines (slurp "inputs/day7.txt")) ))

(defn extractRule [rule]
  {:subject (first (s/split rule #" contain "))
   :contents (map #(subs % 2) (s/split (last (s/split rule #" contain ")) #", "))})

(defn containsBag? [input bag]
  (let [rule (extractRule input)
        contents (:contents rule)]
    (reduce #(or %1 %2) (map #(not (nil? (re-matches (re-pattern (str bag "[s]*")) %))) contents))))

(defn containsList [bag]
  (map #(s/replace % #"s$" "") (map #(:subject (extractRule %)) (filter #(containsBag? % bag) day7Input))))

(def allBags
  (zipmap (set (map #(s/replace % #"s$" "") (flatten (map #(list (:subject %) (:contents %)) (map extractRule day7Input))))) (range)))

(def containsRules (map #(containsList (first %)) allBags))

(defn getContainingBags [starterBag]
  (nth containsRules (allBags starterBag)))

(defn countContainers [starterBag]
  (loop [list (getContainingBags starterBag)
         prevCount 0
         currList '()]
    (if (= (count list) prevCount)
      currList
      (recur
        (set (flatten (map getContainingBags list)))
        (count list)
        (set (concat currList list))))))

(defn day7Part1 []  (countContainers "shiny gold bag"))
(defn day7Part2 [] nil)