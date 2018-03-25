(ns my-slack-bot.core
  (:gen-class)
  (:require [clojure.string :refer [lower-case]]
            [clojure.tools.cli :refer [cli]]
            [clojure.tools.logging :as log]
            [slack-rtm.core :refer [connect send-event sub-to-event]]))

(def dic (read-string (slurp "dic.edn")))

(defn message-receiver [dispatcher event]
  (let [{:keys [channel text]} event]
    (log/info {:incoming-text event})
    (if-let [df (dic (lower-case text))]
      (send-event dispatcher {:type "message" :text df :channel channel}))))

(defn -main [& args]
  (let [[{:keys [token]} args banner] (cli args
                                           ["-h" "--help" "Show help" :flag true :default false]
                                           ["-t" "--token TOKEN" "Bot User OAuth Access Token"])]
    (if token 
      (let [{:keys [events-publication dispatcher start]} (connect token)]
        (sub-to-event events-publication :message #(message-receiver dispatcher %)))
      (do 
        (log/error "Token not specified\n" banner)
        (System/exit 1)))))
