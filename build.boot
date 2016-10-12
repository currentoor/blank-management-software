(set-env!
 :source-paths   #{"src/server" "src/client" "src/shared"}
 :resource-paths #{"resources"}
 :dependencies '[[adzerk/boot-cljs "1.7.228-1" :scope "test"]
                 [adzerk/boot-reload "0.4.12" :scope "test"]

                 [adzerk/boot-cljs-repl   "0.3.0" :scope "test"]
                 [com.cemerick/piggieback "0.2.1"  :scope "test"]
                 [weasel                  "0.7.0"  :scope "test"]

                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]

                 [environ "1.0.3"]
                 [boot-environ "1.0.3"]

                 [org.danielsz/system "0.3.1"]
                 [org.clojure/tools.nrepl "0.2.12"]

                 [com.taoensso/timbre    "4.3.1"]
                 [org.clojure/core.async "0.2.374"]
                 [bidi                   "2.0.9"]
                 [kibu/pushy             "0.3.6"]
                 [com.taoensso/sente   "1.8.1"]
                 [com.taoensso/truss   "1.1.1"]
                 [com.taoensso/encore "2.36.2"]

                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [compojure "1.4.0"]

                 [com.datomic/datomic-free "0.9.5206"]
                 [org.omcljs/om            "1.0.0-alpha45"]
                 [navis/untangled-client   "0.5.7"]
                 [navis/untangled-server   "0.6.2"]
                 [navis/untangled-datomic  "0.4.9"]
                 [untangled-starter "0.0.1-SNAPSHOT"]
                 [navis/untangled-spec     "0.3.8" :scope "test"]

                 [devcards          "0.2.1-6"]
                 [sablono           "0.6.0"]
                 [camel-snake-kebab "0.3.2"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[ubd.systems :refer [dev-system prod-system]]
 '[environ.boot :refer [environ]]
 '[system.boot :refer [system run]])

(deftask dev
  "Run a restartable system in the Repl"
  []
  (comp
   (environ :env {:http-port "3000"})
   (watch :verbose true)
   (speak)
   (system :sys #'dev-system :auto true :files ["handler.clj"])
   (reload)
   (cljs-repl)
   (cljs :source-map true)))

(deftask prod-run
  "Run a prod system from the command line"
  []
  (comp
   (environ :env {:http-port "8008"
                  :repl-port "8009"})
   (cljs :optimizations :advanced)
   (run :main-namespace "ubd.core" :arguments [#'prod-system])
   (wait)))
