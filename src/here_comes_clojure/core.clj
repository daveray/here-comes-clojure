(ns here-comes-clojure.core
  (:require [clojure.string :as cs]))


(def tldr-slide1
"TL;DR - Just give me a REPL!
----------------------------

$ java -version
java version \"1.6.0_24\"
Java(TM) SE Runtime Environment (build 1.6.0_24-b07-334-10M3326)
Java HotSpot(TM) 64-Bit Server VM (build 19.1-b02-334, mixed mode)
$ wget -q --no-check-certificate \\
    http://github.com/downloads/clojure/clojure/clojure-1.2.0.zip
$ unzip -q clojure-1.2.0.zip 
$ java -jar clojure-1.2.0/clojure.jar 
Clojure 1.2.0
user=> (println \"Yep\")
Yep
nil
user=> ^C
$ echo '(ns hello) (println \"Hello\")' > hello.clj
$ java -jar clojure-1.2.0/clojure.jar hello.clj 
Hello")

(def tldr-slide2
"TL;DR - Just tell me how to run a script!
-----------------------------------------

$ echo '(ns hello) (println \"Fine, gosh!\")' > hello.clj
$ java -jar clojure-1.2.0/clojure.jar hello.clj 
Fine, gosh!
")

(def expressions-slides
  (str "Expression Evaluation
---------------------

Most expressions evaluate to themselves

  * (nil)     nil          --> " "nil" "
  * (integer) 876          --> " 876 "
  * (double)  8.76         --> " 8.76 "
  * (string)  \"A String\"   --> " "\"A String\"
  * (keyword) :x           --> " :x "
  * (vector)  [1 2 3]      --> " [1 2 3] "
  * (map)     {:x 1, :y 2} --> " { :x 1 :y 2 } "
  * (set)     #{:foo :bar} --> " #{ :foo :bar }))

(def symbol-eval-slide
  (str "Symbol Evaluation
-----------------

... but symbols, evaluate to whatever they refer to:

        user=> (def drunk { :type :mean })
        #'user/drunk

        user=> drunk
        { :type :mean }

Symbols are used to give names to things (functions, objects, etc)"))

(def list-eval-slide
  (str "List Evaluation
---------------

... and list expressions are treated as function (or macro)
    calls:

          (function-name args ...)

    (+ 1 2 3 4 5 6) --> " (+ 1 2 3 4 5 6) "

    (reduce + (range 0 7)) --> " (reduce + (range 0 7)) "
    ^         ^
    |         `- nested list eval'd first
    |
    `- outer list eval'd last

Lists are where all the parens live."))

(def function-slide
  (str "Functions
---------

Functions, not objects, are the bread and butter of Clojure

Define functions with the (fn) function:

  (def retort (fn [s] (str \" ... that's what she said.\")))

    user=> (retort \"Java's so imperative\")
    \" ... that's what she said.\"

or better yet, the (defn) macro:

  (defn retort [s] (str \" ... that's what she said.\"))
          ^     ^
   name --'     `-- vector of arguments"))

(def function-bonus-slide
  (str "Functions (bonus slide)
-----------------------

Functions can have doc-strings and multiple arity:

(defn add
  \"A crappier version of +. Don't do this.\"
  ([a b] (+ a b))
  ([a b c] (+ a (add b c))))

Function literal (lambda) syntax:

      % refers to first arg, use %N for more
                  |
                  v
  user=> (map #(* % 2) (range 0 10))
  (0 2 4 6 8 10 12 14 16 18)"))

(def flow-control-slide
  (str "Flow Control
------------

      (if test 
        then    ; evaluated if test is truey
        else)   ; evaluated if test is falsey

e.g.

      (defn pluralize [n s] 
        (if (= n 1) 
          s 
          (str s \"s\")))

### Only false and nil are falsey. EVERYTHING else it truey ###"))

(def loop-recur-slide
  (str "loop/recur
----------

* Basis for iteration
* Rarely necessary. Use map, reduce and friends instead.
 
  (defn sum [n]
    \"Sum numbers from 1 to n inclusive.\"
    (loop [current 1 acc 0]
      (if (> current n)
        acc
        (recur (inc current) (+ current acc)))))

* Solution for lack of tail-call optimization, 
     recursion on JVM"))

(def collections-slide1
"Collections
-----------

* Vector

  [2 4 6 8 10]    or     (vector 2 4 6 8 10)

* Map

      { :title \"Breakfast of Champions\" :author  \"Vonnegut, Kurt\" }
  or
      (hash-map :title \"Breakfast of Champions\" :author  \"Vonnegut, Kurt\")

* Set

      #{ \"John\", \"Paul\", \"George\", \"Ringo\" }
  or
      (hash-set \"John\", \"Paul\", \"George\", \"Ringo\")")

(def collections-slide2
"More Collections
----------------

* Also, List, but Vector is generally preferred, except in macros

  '(1 2 3)  or  (list 1 2 3)

* Consistent set of operations on all collections:

  (conj [1 2 3] :four)      --> [1 2 3 :four]
  (conj {:x 1 :y 2} [:z 3]) --> {:z 3, :x 1, :y 2}
  (conj #{ 1 2 3 } :four)   --> #{1 2 3 :four}

  Also, (disj), (assoc), (dissoc), etc. All have appropriate implementation
  for each datastructure.")

(def persistence-slide
"Persistence
-----------

* Clojure defaults to immutability

  user=> (def point {:x 1 :y 2})
  #'user/point
  user=> (assoc point :z 3)
  {:z 3, :x 1, :y 2}              ; returns a new map
  user=> point
  {:x 1, :y 2}                    ; point is unchanged!

* Persistant data structures to efficiently share structure")

(def seqs-slide
"Seq
---

* All collections (and more) implement ISeq interface:

  (first [1 2 3])   -->   1
  (rest [1 2 3])    -->   (2 3)
  (cons 0 [1 2 3])  -->   (0 1 2 3)

* Rich library of sequence functions 
  * map, reduce, filter, partition, etc
  * All work consistently across ISeq implementors

* A sequence can be _lazy_, i.e. realize sequence as needed.
  * Reduce memory usage
  * Supports infinite sequences")

(def java-interop-slide 
"Java Interop
------------")

(def slides [
"            ???????????=              
        ???????????????????           
      ???????????????????????.        
    +??++??????????????????????       
                      +?????????      
       ::      ~~~~~~.  .????????   
   +$$$$I  ,?   ~~~~~~~~  +???????  
  $$$$$$  ????  .~~~~~~~~. .??????: 
 $$$$$$  ??????  :~~~~~~~~  ??????? 
$$$$$$. =???????  ~~~~~~~~:  ??????,
$$$$$$  ????????  ~~~~~~~~~  ??????~
$$$$$$  ??????? , .~~~~~~~~  ??????~
$$$$$$. =?????  ?: :~~~~~~:  ??????,
$$$$$$$  ????= =??  ~~~~~~  ??????+ 
~$$$$$$7  ??+ .???.  ~~~~. .??????  
 ?$$$$$$I  ,  ?????   ~~  +????+    
  $$$$$$$$.   +?????.               Here Come's Clojure
   $$$$$$$$$?         ?:       ,    -------------------
    I$$$$$$$$$$$$$$$$$$$$$$$$$7     
      $$$$$$$$$$$$$$$$$$$$$$$.      CraftsmanGuild, April 5, 2011
        7$$$$$$$$$$$$$$$$$7         Dave Ray (@darevay)
            $$$$$$$$$$$?            "

"What's a Clojure?
-----------------

* A Lisp variant, implemented on the JVM
* Dynamic
* Pragmatic
* Opinionated"

expressions-slides
symbol-eval-slide
list-eval-slide

"Two Minor Syntactical Notes
---------------------------

; I'm a comment. I'm probably already out of date.
^
`- Semi-colons are comments 'til the end of line.

[:Also, :commas :are :whitespace,
  :ignored :by :Clojure, :and :used :for :clarity.]"

"Language Syntax
---------------

___ You Just Learned It!! ___


A list
  |       A vector 
  |           |        
  v           v
  (defn main- [& args]
    (println \"Clojure code is expressed with its own datastructures!*\"))
        ^                 ^
        |                 |
        `- A symbol       `- A string

* It's /homoiconic/"

function-slide
function-bonus-slide
flow-control-slide
loop-recur-slide

collections-slide1
collections-slide2
seqs-slide


tldr-slide1
tldr-slide2

"That's it.
----------"
])

(def current (atom slides))

(def height 21)
(def width 70)

(def separator (cs/join (take width (repeat \-))))


(defn get-padding-size [content]
  (let [line-count (count (cs/split content #"\n"))
        excess     (- height (+ line-count 5))]
    [(quot excess 2)
     (quot excess 2)]))

(defn prepare-content [content prefix]
  (cs/join "\n"
    (map #(str prefix %)
        (cs/split content #"\n"))))

(defn prepare-padding [n prefix]
  (prepare-content (cs/join (take n (repeat "\n"))) prefix))

(defn print-current! []
  (let [content (first @current)
       [top bottom] (get-padding-size content)]
    (println separator)
    (println (prepare-padding top     "|  "))
    (println (prepare-content content "|  "))
    (println (prepare-padding bottom  "|  "))
    (println separator)))

(defn advance! [] 
  (do
    (println "`\n")
    (print-current!)
    (swap! current next)
    (list "Here Come's Clojure", :CrafstmanGuild, "@darevay", 
     (- (count slides) (count @current)))))

(defn begin! []
  (do
    (println (count slides) "slides")
    (reset! current slides)
    (advance!)))

