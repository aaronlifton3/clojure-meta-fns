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

(defn loop-fact [x]
  (loop [n x f 1]
    (if (= n 1) f
      (recur (dec n) (* f n)))))
    

(defn factorials []
    (letfn [(factorial-seq [n fact]
       (lazy-seq
         (cons fact (factorial-seq (inc n) (* (inc n) fact)))))]
      (factorial-seq 1 1)))

(def facts (lazy-cat [1] (map * facts (iterate inc 2))))
(def facts2 (reductions * (iterate inc 1))) 

(defn recur-fact 
    ([n] 
        (factorial n 1)) 
    ([n acc] 
        (if  (= n 0)   acc 
             (recur (dec n) (* acc n)))))

(def funs2 {:loop-fact 5 :recur-fact 4})
(def funs '{loop-fact 5 recur-fact 4})

;(doseq [[k v] {"loop-test" 7 "factorial" 4}]
;  (prn k v))

(map #((resolve (symbol %1)) %2) ["loop-test" "factorial"] [1 2])  

(doseq [[k,v] {"loop-test" 4 "factorial" 4}]
  (prn (#((resolve (symbol k)) v))))

(defmacro assert-equals [actual expected]
    `(let [~'actual-value ~actual]
        (when-not (= ~'actual-value ~expected)
            (throw
                (AssertionError.
                   (str "Expected '" '~actual "' to be " ~expected " but was " ~'actual-value))))
        (when (= ~'actual-value ~expected)
            (str "True: '" '~actual "' => '" ~'actual-value "' = '" ~expected "'"))))

; Test assert-equals macro
(macroexpand-1 '(assert-equals (inc 5) 5))
(assert-equals (inc 5) 5)
(assert-equals (inc 5) 6)


(prn (assert-equals 1 1))
;(apply (loop-test)
;(factorial 4)
