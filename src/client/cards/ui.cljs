(ns cards.ui
  (:require
   [cards.navbar]
   [devcards.core :as dc]))

(defn start []
  (dc/start-devcard-ui!))

(start)
