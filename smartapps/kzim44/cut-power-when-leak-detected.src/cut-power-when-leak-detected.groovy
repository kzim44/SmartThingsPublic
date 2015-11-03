/**
 *  HelloWorld
 *
 *  Copyright 2015 Kevin Zimmerman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Cut Power When Leak Detected",
    namespace: "kzim44",
    author: "Kevin Zimmerman",
    description: "When water is detected, turn off a switch",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
    section("When a leak is detected:") {
        input "theleak", "capability.waterSensor", required: true, title: "Which?"
    }
    section("Turn off this switch") {
        input "theswitch", "capability.switch", required: true
    }
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	// TODO: subscribe to attributes, devices, locations, etc.
    subscribe(theleak, "water.wet", leakDetectedHandler)
}

// TODO: implement event handlers
def leakDetectedHandler(evt) {
    log.debug "leakDetectedHandler called: $evt"
    theswitch.off()
}