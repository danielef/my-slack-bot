(ns my-slack-bot.core
  (:require [clojure.core.async :refer [go-loop <!]]
            [clojure.string :refer [lower-case]]
            [slack-rtm.core :refer [connect send-event sub-to-event]]))


(def dic (read-string (slurp "dic.edn")))

(defn message-receiver [dispatcher event]
  (let [{:keys [channel text]} event]
    (println {:incoming-text event})
    (if-let [df (dic (lower-case text))]
      (send-event dispatcher {:type "message" :text df :channel channel}))))

(defn -main [& args]
  (let [{:keys [events-publication dispatcher start]} (connect "Bot User OAuth Access Token")]
    (sub-to-event events-publication :message #(message-receiver dispatcher %))))
