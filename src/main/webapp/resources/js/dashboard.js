var playSoundInterval;
var smokeCoAlarmJsonData = {};

var parseSmokeCoAlarmJsonData = function(data) {
    try {
        smokeCoAlarmJsonData = eval('(' + data + ')');
    } catch (e) {
        console.log(e.message);
    }
}

var playBeep = function() {
    new Audio($("#beepUrl").val()).play();
}

var playSound = function() {
    if (playSoundInterval == undefined) {
        playBeep();
        playSoundInterval = setInterval(playBeep, 2000);
    }
}

var stopSound = function() {
    clearInterval(playSoundInterval);
    playSoundInterval = undefined;
}

var applyEffect = function() {
    playSound();
    $("#smokeCoAlarmImg")[0].underEffect = true;
    $("#smokeCoAlarmExclImg").removeClass("unvisible");
    $("#smokeCoAlarmImg").pulse({opacity: 0.4}, {duration : 600, pulses : -1});
}

var cancelEffect = function() {
    stopSound();
    $("#smokeCoAlarmImg").pulse('destroy');
    $("#smokeCoAlarmImg")[0].underEffect = false;
    $("#smokeCoAlarmExclImg").addClass("unvisible");
    $("#smokeCoAlarmImg").attr("style", "opacity: 1");
}

var displaySmokeCoAlarm = function() {
    $("#smokeCoAlarm").removeClass("hidden");
    $("#smokeCoAlarmImg")[0].underEffect = false;
    smokeCoAlarmState(smokeCoAlarmJsonData.smoke_alarm_state);
}

var smokeCoAlarmState = function(state) {
    $("#smokeCoAlarmImg").attr("src", $("#imagesPath").val() + "smoke_device_" + state + ".png");
    startEffect(state);
}

var startEffect = function(state) {
    if (state != "ok") {
        if (!$("#smokeCoAlarmImg")[0].underEffect) {
            applyEffect();
        }
    } else if ( $("#smokeCoAlarmImg")[0].underEffect) {
        cancelEffect();
    }
}

var disconnect = function() {
    Firebase.goOffline();
    location.href = $("#resetUrl").val();
}

var subscribeSmokeCoAlarm = function() {
    var access_token = $("#token").val();
    var fireBaseRef = new Firebase($("#fireBaseHost").val());
    fireBaseRef.authWithCustomToken(access_token, function(error, authData) {
        if (error) {
            disconnect();
        } else {
            fireBaseRef.child(".info/connected").on("value", function(connectedSnap) {
                if (!(connectedSnap.val() === true)) {
                    disconnect();
                }
            });
        }
    });
    fireBaseRef.on("value", function(snapshot) {
        try {
            smokeCoAlarmState(snapshot.val().devices.smoke_co_alarms[smokeCoAlarmJsonData.device_id].smoke_alarm_state);
        } catch (e) {
            console.log(e.message);
        }
    });
}

$(function() {
    if (smokeCoAlarmJsonData.smoke_alarm_state != undefined) {
        displaySmokeCoAlarm();
        subscribeSmokeCoAlarm();
    }
});
