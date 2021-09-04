(ns org.msync.spring-clj.core
  (:require [org.msync.spring-boot-bugger :as bugger]
            [compojure.core :refer :all]
            [compojure.route :refer [not-found]]
            [clojure.string])
  (:import [java.util.logging Logger]
           [org.springframework.context ApplicationContext]))

(defonce logger (Logger/getLogger (str *ns*)))

(defroutes app
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
  (not-found "<h1>Page not found</h1>"))

(defn main
  "Set this as your entry-point for the Clojure code in your spring-boot app.
  Gets the ApplicationContext object as an argument - which you are free to ignore or use."
  [^ApplicationContext application-context]
  ;; Any init-routines, including those that need the application context.
  (.info logger (str "[spring-clj] Initializing clojure app..."))
  (bugger/set-handler! app))
