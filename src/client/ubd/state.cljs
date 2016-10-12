(ns ubd.state
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defonce init-data
  (atom {}))
