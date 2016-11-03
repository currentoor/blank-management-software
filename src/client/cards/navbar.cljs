(ns cards.navbar
  (:require
   [ubd.aum :as aum :refer [inspect defcomponent]]
   [om.next :as om]
   [devcards.core :as dc :refer-macros [defcard]]))

(defcomponent NavBar {}
  (render [this]
    (inspect [:h1 "Hello World!"])))

(defcard navbar
  (fn [state node]
    (ui-navbar (inspect @state)))
  {:all-tabs []}
  {:inspect-data true})
