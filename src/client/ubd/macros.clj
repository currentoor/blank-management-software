(ns ubd.macros)

(defn- inspect-1 [expr]
  `(let [result# ~expr]
     (js/console.log (str (pr-str '~expr) " => "
                          (with-out-str (cljs.pprint/pprint result#))))
     result#))

(defmacro inspect
  "Debugging macro for inspecting a value in the js console."
  [& exprs]
  `(do ~@(map inspect-1 exprs)))
