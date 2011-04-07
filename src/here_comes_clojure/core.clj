(ns here-comes-clojure.core
  (:require [clojure.string :as cs]
            [clojure.java.browse]))

(def browse clojure.java.browse/browse-url)



(def slides [
             
;---------------------------------------------------------------------------------
            
             
"            400000000000              
        4000000000000000000           
      40000000000000000000000.        
    400000000000000000000000000       
                      0000000000      
     ~~~~      000000.   00000000   
   ~~~~~.  ~~   00000000   0000000  
  ~~~~~~  ~~~~  000000000.  0000000 
 ~~~~~~  ~~~~~~  000000000  0000000 
~~~~~~. ~~~~~~~~  00000000.  000000.
~~~~~~  ~~~~~~~~  000000000  0000000
~~~~~~  ~~~~~~~ ~  00000000  0000000
~~~~~~. ~~~~~~  ~~ 0000000.  0000007
~~~~~~~  ~~~~~ ~~~  000000  0000000 
~~~~~~~~  ~~~ ~~~~~  0000.  000000  
 ~~~~~~~~  ~  ~~~~~   00   00000    
  ~~~~~~~~.   ~~~~~~.               Here Comes Clojure
   ~~~~~~~~~          ''       ~    -------------------
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~     
      ~~~~~~~~~~~~~~~~~~~~~~~.      CraftsmanGuild, April 5, 2011
        ~~~~~~~~~~~~~~~~~~~         Dave Ray (@darevay)
            ~~~~~~~~~~~~            "

;---------------------------------------------------------------------------------
            

"What's a Clojure?
-----------------

* A Lisp variant, implemented on the JVM
* Dynamic (yet compiled)
* Pragmatic
* Opinionated"
             
;---------------------------------------------------------------------------------
            
(str "Expression Evaluation
---------------------

Most expressions evaluate to themselves ...

  * (nil)     nil          --> " "nil" "
  * (integer) 876          --> " 876 "
  * (double)  8.76         --> " 8.76 "
  * (string)  \"A String\"   --> " "\"A String\"
  * (keyword) :x           --> " :x "
  * (vector)  [1 2 3]      --> " [1 2 3] "
  * (map)     {:x 1, :y 2} --> " { :x 1 :y 2 } "
  * (set)     #{:foo :bar} --> " #{ :foo :bar })            

;---------------------------------------------------------------------------------
 
"Symbol Evaluation
-----------------

... but symbols evaluate (they are /resolved/) to whatever they 
    refer to:

        user=> (def drunk { :type :mean })
        #'user/drunk

        user=> drunk
        { :type :mean }

Symbols are used to give names to things (functions, objects, etc)"
             
;---------------------------------------------------------------------------------

(str "List Evaluation
---------------

... and list expressions are treated as function (or macro) calls:

          (function-name args ...)

    (+ 1 2 3 4 5 6) --> " (+ 1 2 3 4 5 6) "

    (reduce + (range 0 7)) --> " (reduce + (range 0 7)) "
    ^         ^
    |         `- nested list eval'd first
    |
    `- outer list eval'd last

Lists are where all the parens live.")           
             
;---------------------------------------------------------------------------------
            

"Two Minor Syntactical Notes
---------------------------

; I'm a comment. I'm probably already out of date.
^
`- Semi-colons are comments 'til the end of line.

[:Also, :commas :are :whitespace,
  :ignored :by :Clojure, :and :used :for :clarity.]"
             
;---------------------------------------------------------------------------------
            

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

* It's /homoiconic/ (http://en.wikipedia.org/wiki/Homoiconicity)

* Code as data  (http://clojure.org/reader)"

             
;---------------------------------------------------------------------------------
            
"Functions
---------

Functions, not objects, are the bread and butter of Clojure

Define functions with the (fn) special form:

  (def retort (fn [s] (str \" ... that's what she said.\")))

    user=> (retort \"Java's so imperative\")
    \" ... that's what she said.\"

or better yet, the (defn) macro:

  (defn retort [s] (str \" ... that's what she said.\"))
          ^     ^
   name --'     `-- vector of arguments"
 
;---------------------------------------------------------------------------------
            
"Functions (bonus slide)
-----------------------

Functions can have doc-strings and multiple arity:

(defn add
  \"A crappier version of +. Don't do this.\"
  ([a b]   (+ a b))
  ([a b c] (+ a (add b c))))

Function literal (lambda) syntax:

      % refers to first arg, use %N for more
                  |
                  v
  user=> (map #(* % 2) (range 0 10))
  (0 2 4 6 8 10 12 14 16 18)"

;---------------------------------------------------------------------------------
            
"Truey / Falsey and the (if) form
---------------------------------

### Only false and nil are falsey. EVERYTHING else it truey ###

      (if test 
        then    ; evaluated if test is truey
        else)   ; evaluated if test is falsey

e.g.

      (defn pluralize [n s] 
        (if (= n 1) 
          s 
          (str s \"s\")))
"

;---------------------------------------------------------------------------------
            
"loop/recur
----------

* Basis for iteration

* Less necessary than you think. 
 
        (defn sum [n]
          \"Sum numbers from 1 to n inclusive.\"
          (loop [current 1 acc 0]
            (if (> current n)
              acc
              (recur (inc current) (+ current acc)))))
  
  Use map, reduce and friends instead:

        (defn sum [n] (reduce + (range 1 (inc n))))

* Solution for lack of tail-call optimization, 
     recursion on JVM"
             
;---------------------------------------------------------------------------------
            

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
      (hash-set \"John\", \"Paul\", \"George\", \"Ringo\")

* /Vectors, Maps, and Sets instances are also functions/"
             
;---------------------------------------------------------------------------------
            
"More Collections
----------------

* Also, List, but Vector is generally preferred, except in macros

  '(1 2 3)  or  (list 1 2 3)

* Consistent set of operations on all collections:

  (conj [1 2 3]     :four)  --> [1 2 3 :four]
  (conj {:x 1 :y 2} [:z 3]) --> {:z 3, :x 1, :y 2}
  (conj #{ 1 2 3 }  :four)  --> #{1 2 3 :four}

  Also, (disj), (assoc), (dissoc), etc. All have appropriate implementation
  for each datastructure.

* See also http://clojure.org/data_structures"
             
;---------------------------------------------------------------------------------

"Persistence
-----------

* Clojure defaults to immutability

  user=> (def point {:x 1 :y 2})
  #'user/point
  user=> (assoc point :z 3)
  {:z 3, :x 1, :y 2}              ; returns a new map
  user=> point
  {:x 1, :y 2}                    ; point is unchanged!

* Persistent data structures to efficiently share structure

http://www.infoq.com/presentations/Value-Identity-State-Rich-Hickey"

;---------------------------------------------------------------------------------
            
"Seq
---

* All collections (and more) implement ISeq interface:

  (first   [1 2 3])   -->   1
  (rest    [1 2 3])   -->   (2 3)
  (cons  0 [1 2 3])   -->   (0 1 2 3)

* Rich library of sequence functions 
  * map, reduce, filter, partition, etc
  * All work consistently across ISeq implementors

* A sequence can be _lazy_, i.e. realize sequence as needed.
  * Reduce memory usage
  * Supports infinite sequences

http://clojure.org/sequences"

;---------------------------------------------------------------------------------
             
"Binding Forms
-------------

* Use (let) and friends to bind expressions to /immutable/ local vars

        (let [name1 value1  \\
              name2 value2  |-- This is a \"binding form\"
              ...]          /
          ...)

  e.g.

        (defn slope [a b]
          (let [dx (- (:x a) (:x b))
                dy (- (:y a) (:y b))]
            (/ dy dx)))

* Bindings established in order. Earlier bindings may be used later.

* Binding forms are used in many macros, (with-open), (loop), (for), etc."

;---------------------------------------------------------------------------------

"Destructuring Forms
-------------------

* Like Ruby or Python \"multiple assignment\", but more powerful.

* Used in both binding forms and argument lists.

* Destructure a vector (or any seq):
 
      This vector is like a template
      that receives vector values
               |
               v
    (let [[a b c & more] [9 8 7 6 5 4 3]]
      (println \"a =\" a \", b =\" b \", c =\" c \", more =\" more))

    -->  a = 9 , b = 8 , c = 7 , more = (6 5 4 3)"

;---------------------------------------------------------------------------------

"Destructuring
-------------

* Destructure a map (with arbitrary nesting!):

           Nested
           vector    +- This 'backward' map is also like a template
             |       |
             v       v
    (let [{[x y] :center radius :radius}  {:center [100 200], :radius 2.0}]
      (println \"x =\" x \", y =\" y \", radius =\" radius))

    -->   x = 100 , y =  200 , radius = 2.0

* Use map destructuring sugar to type less:

            { name :name address :address phone :phone email :email }

  becomes:

            { :keys [name address phone] }

  / See http://blog.jayfields.com/2010/07/clojure-destructuring.html /"

;---------------------------------------------------------------------------------

"Java Interop
------------

* Create a new Java object:   (URL. \"http://google.com\")
                                  ^
                                  `-- Note the trailing dot on the class name

* Call a method on a Java object:           method    target
                                              |          |
                                              v          v
                                        (.toExternalForm url)

* Call a static method:

  (System/setProperty \"java.library.path\" \"Why won't my DLLs load?\")

* Access a static member:

  (.setForeground label Color/ORANGE)

/ http://clojure.org/java_interop /"
             
;---------------------------------------------------------------------------------
            
"More Java Interop
-----------------

* Collections implement java.util.List and other interfaces as appropriate

* Clojure functions implement Runnable & Callable:

    (SwingUtilities/invokeLater #(.repaint panel))

* (->) threading operator cleans up long method chains:

              ((JTextField) event).getSource().getDocument().getLength();
    becomes: 
              (-> event .getSource .getDocument .getLength)

    /That's 8 _fewer_ parens than the same Java code!/

* (doto) helps with initializing 'bean-y' objects:

    (doto (JLabel.)
      (.setText      \"HI\")
      (.setForeground Color/ORANGE)
      (.setIcon       (ImageIcon. \"hi.png\")))"
             
;---------------------------------------------------------------------------------

"(def surface {:scratched? :barely})
-----------------------------------

* Namespaces

* Concurrency and state management

* Protocol, Multi-methods, and Ad-hoc Hierarchies

* Testing

* Build tools and dev environment

* DSLs

* Libraries"

;---------------------------------------------------------------------------------

"Further Reading and Resources
-----------------------------

* USE THE SOURCE! SERIOUSLY!  https://github.com/clojure/clojure 

* clojure.org - Project home page

* clojuredocs.org - User-annotated library documentation

* \"The Joy of Clojure\", Fogus & Houser (http://joyofclojure.com)"

;---------------------------------------------------------------------------------

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
Hello"

;---------------------------------------------------------------------------------

"TL;DR - Just tell me how to run a script!
-----------------------------------------

$ echo '(ns hello) (println \"Fine, gosh!\")' > hello.clj
$ java -jar clojure-1.2.0/clojure.jar hello.clj 
Fine, gosh!"

;---------------------------------------------------------------------------------

"That's it.
----------

https://github.com/daveray/here-comes-clojure"
])

;---------------------------------------------------------------------------------
;---------------------------------------------------------------------------------
;---------------------------------------------------------------------------------

(def current (atom slides))

(def height 21)
(def width 70)
(def content-prefix "|  ")

(def separator (cs/join (take width (repeat \-))))

(defn get-padding-size [content]
  (let [line-count (count (cs/split content #"\n" -1))
        excess     (- height (+ line-count 5))]
    [(quot excess 2)
     (quot excess 2)]))

(defn prepare-content [content prefix]
  (cs/join "\n"
    (map #(str prefix %)
        (cs/split content #"\n" -1))))

(defn prepare-padding [n prefix]
  (prepare-content (cs/join (take n (repeat "\n"))) prefix))

(defn print-current! []
  (let [content     (first @current)
       [top bottom] (get-padding-size content)]
    (println separator)
    (println (prepare-padding top     content-prefix))
    (println (prepare-content content content-prefix))
    (println (prepare-padding bottom  content-prefix))
    (println separator)))

(defn advance! [] 
  (do
    (when-not @current
      (reset! current slides))
    (println "`\n\n\n\n\n\n\n\n")
    (print-current!)
    (swap! current next)
    (list "Here Comes Clojure", :CrafstmanGuild, "@darevay", 
     (- (count slides) (count @current)))))

(defn begin! []
  (do
    (println (count slides) "slides")
    (reset! current slides)
    (advance!)))

