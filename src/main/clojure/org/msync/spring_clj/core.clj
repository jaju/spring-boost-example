;; [[file:../../../../../../README.org::*The Clojure Code][The Clojure Code:1]]
(ns org.msync.spring-clj.core
  (:require [org.msync.spring-boost :as boost]
            [compojure.core :refer :all]
            [compojure.route :refer [not-found]]
            [clojure.string]
            [taoensso.sente :as sente])
  (:import [java.util.logging Logger]
           [org.springframework.context ApplicationContext]))

(defonce logger (Logger/getLogger (str *ns*)))

#_(let [{:keys [ch-recv send-fn connected-uids
              ajax-post-fn ajax-get-or-ws-handshake-fn]}
      (sente/make-channel-socket! (get-sch-adapter) {})]

  (def ring-ajax-post                ajax-post-fn)
  (def ring-ajax-get-or-ws-handshake ajax-get-or-ws-handshake-fn)
  (defonce ch-chsk                       ch-recv) ; ChannelSocket's receive channel
  (defonce chsk-send!                    send-fn) ; ChannelSocket's send API fn
  (defonce connected-uids                connected-uids) ; Watchable, read-only atom
  )

(defroutes app
  "Root hello-world GET endpoint, and another echo end-point that handles both GET and POST.
  The :body entry in the request-map comes in either as a map for JSON requests, or as a String
  for other types."
  (GET "/" [:as {query-string :query-string}]
       (str "<h1>Hello World.</h1>"
            (if-not (clojure.string/blank? query-string) (str "We received a query-string " query-string))))
  (GET "/echo/:greeter" [greeter]
       {:status 200
        :headers {:content-type "application/json"}
        :body {:greeting (str "Hello, " greeter)}})
  (POST "/echo/:greeter" [greeter :as request]
        {:status 200
         :headers {:content-type "application/json"}
         :body {:greetings (str "Hello, " greeter)
                :echo (:body request)}})
  #_(GET  "/chsk" req (ring-ajax-get-or-ws-handshake req))
  (not-found "<h1>Page not found</h1>"))

(defn main
  "Set this as your entry-point for the Clojure code in your spring-boot app.
  Gets the ApplicationContext object as an argument - which you are free to ignore or use."
  [^ApplicationContext application-context]

  (.info logger (str "[spring-clj] Initializing clojure app..."))
  (boost/set-handler! app))
;; The Clojure Code:1 ends here
