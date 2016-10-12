(ns ubd.core
  (:require
   [ubd.aum :as aum :refer [inspect defcomponent]]
   [ubd.ui :as ui]
   [ubd.app :as app]))

(defn run []
  ;; On load stuff goes here.
  (app/remount-app!))
