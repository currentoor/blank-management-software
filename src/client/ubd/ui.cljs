(ns ubd.ui
  (:require
   [ubd.aum :as aum :refer [inspect defcomponent]]
   [om.next :as om]
   [om.dom :as dom :include-macros true]))

(defcomponent Root {}
  (render [this]
    [:h1 "Hello World!"]))
