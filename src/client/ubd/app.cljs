(ns ubd.app
  (:require
   [ubd.state :as st]
   [ubd.ui :as ui]
   [untangled.client.core :as uc]
   [untangled.client.data-fetch :as df]
   [untangled.client.logging :as log]
   [ubd.aum :as aum :refer [inspect defcomponent]]
   [om.core :as om :include-macros true]
   [om.dom :as dom :include-macros true]))

(defonce app (atom nil))

(defn on-app-started [{:keys [networking initial-state] :as app}]
  (let [reconciler (:reconciler app)]
    ;; (df/load-data reconciler [{:dashboards (om/get-query Dashboard)}
    ;;                           {:reports (om/get-query Report)}
    ;;                           {:current-user (om/get-query User)}]
    ;;               :without #{:data-source/current-stream}
    ;;               :post-mutation 'post-initial-load)
    ))

;; (defn network-error-callback [state error status]
;;   (auth/global-error-handler error))

(defn remount-app! []
  (if-not @app
    (reset! app (uc/new-untangled-client
                 ;; :request-transform auth/with-auth-token
                 :initial-state st/init-data
                 ;; :network-error-callback network-error-callback
                 :started-callback on-app-started)))
  (reset! app (uc/mount @app ui/Root "app")))
