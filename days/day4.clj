(ns days.day4
  (:require [clojure.string :as s]))

(def day4Input (slurp "inputs/day4.txt"))

(def passports (map #(clojure.string/replace % #"\r\n" '" ") (clojure.string/split days.day4/day4Input #"\r\n\r\n")))

(def passportKeys ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"])

(defn getKey [field]
  (first (s/split field #":")))

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn isValidPassport? [passport keys]
  (let [fields (s/split passport #" ")]
    (not
      (>
        (count keys)
        (count
          (filter
            #(in? keys (getKey %))
            fields)))))
  )

(defn validateHeight [elem]
  (or
    (and
      (> (count elem) 2)
      (= '(\i \n) (take-last 2 elem))
      (let [heightStr (subs elem 0 (- (.length elem) 2)) height (try (Integer/parseInt heightStr) (catch NumberFormatException e 0))]
        (and
          (>= height 59)
          (<= height 76))))
    (and
      (> (count elem) 2)
      (= '(\c \m) (take-last 2 elem))
      (let [heightStr (subs elem 0 (- (.length elem) 2)) height (try (Integer/parseInt heightStr) (catch NumberFormatException e 0))]
        (and
          (>= height 150)
          (<= height 193))))))

(def validators {
                  :byr (fn [elem] (and (= 4 (count elem)) (try (Integer/parseInt elem) (catch NumberFormatException e false)) (>= (Integer/parseInt elem) 1920) (<= (Integer/parseInt elem) 2020)))
                  :iyr (fn [elem] (and (= 4 (count elem)) (try (Integer/parseInt elem) (catch NumberFormatException e false)) (>= (Integer/parseInt elem) 2010) (<= (Integer/parseInt elem) 2020)))
                  :eyr (fn [elem] (and (= 4 (count elem)) (try (Integer/parseInt elem) (catch NumberFormatException e false)) (>= (Integer/parseInt elem) 2020) (<= (Integer/parseInt elem) 2030)))
                  :hgt (fn [elem] (validateHeight elem))
                  :hcl (fn [elem] (re-matches #"^#[a-fA-F0-9]{6}$" elem))
                  :ecl (fn [elem] (in? '["amb" "blu" "brn" "gry" "grn" "hzl" "oth"] elem))
                  :pid (fn [elem] (= 9 (count elem)))
                  :cid (fn [_] true)
                  })

(defn validateField [fieldData validators]
  (let [field (s/split fieldData #":")
        fieldName (first field)
        fieldData (last field)]
    (try (((keyword fieldName) validators) fieldData) (catch NullPointerException e false))
   ))


(defn isValidPassport2? [passport keys validators]
  (let [fields (s/split passport #" ")]
    (and
      (not
        (>
          (count keys)
          (count
            (filter
              #(in? keys (getKey %))
              fields))))
      (reduce #(and %1 %2) (map #(validateField % validators) fields))))
  )

(defn day4Part1 [] (count (filter #(isValidPassport? % passportKeys) passports)))
(defn day4Part2 [] (count (filter #(isValidPassport2? % passportKeys validators) passports)))