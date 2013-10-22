(ns myproject.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (doseq [y [x "Hello, World!"]] (prn y)))

(defn loop-test
  [x]
  (loop [i x acc 1]
  (if (zero? i)
    acc
    (recur (dec i) (* acc i)))))

(defn factorial 
    ([n] 
        (factorial n 1)) 
    ([n acc] 
        (if  (= n 0)   acc 
             (recur (dec n) (* acc n)))))

(def funs2 {:loop-test 7 :factorial 4})
(def funs '{loop-test 7 factorial 4})

;(doseq [[k v] {"loop-test" 7 "factorial" 4}]
;  (prn k v))

(map #((resolve (symbol %1)) %2) ["loop-test" "factorial"] [1 2])  

(doseq [[k,v] {"loop-test" 7 "factorial" 4}]
  (prn (#((resolve (symbol k)) v))))

;(apply (loop-test)
;(factorial 4)
