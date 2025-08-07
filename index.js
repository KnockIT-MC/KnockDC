function getJSON(url) {
     request = new XMLHttpRequest();
     request.open("GET", url, false);
     request.send();
     return JSON.parse(request.responseText);
}
function getYAML(url) {
     request = new XMLHttpRequest();
     request.open("GET", url, false);
     request.send();
     return request.responseText;
}

getJSON("https://api.modrinth.com/v2/project/knockdc").versions.forEach(versionID => {
     const version = getJSON("https://api.modrinth.com/v2/project/knockdc/version/" + versionID);
     const versionSelect = document.getElementById("versionSelect");

     const option = document.createElement("option");
     option.value = version.version_number;
     option.text = version.version_number;
     versionSelect.appendChild(option);

     writeConfig(version);
});

function writeConfig(version) {
     document.getElementById("config").innerHTML = "";
     const text = getYAML("https://raw.githubusercontent.com/KnockIT-MC/KnockDC/refs/tags/" + version.version_number + "/src/main/resources/config.yml").split("\n");
     text.forEach(line => {
          if (line.includes("#")) {
               line = "<span style='color: #618B4E'>" + line + "</span>";
          } else {
               if (line.includes(":")) {
                    if (line.split(":")[1].includes(`"`) || line.split(":")[1].includes(`'`)) {
                         line = "<span style='color: #569DD6'>" + line.split(":")[0] + "</span>:" + "<span style='color: #CE9178'>" + line.split(":")[1] + "</span>";
                    } else {
                         line = "<span style='color: #569DD6'>" + line.split(":")[0] + "</span>:" + line.split(":")[1];
                    }
               }
          }
          document.getElementById("config").innerHTML += line + "<br>";
     });
}

document.getElementById("versionSelect").addEventListener("change", function () {
     writeConfig(getJSON("https://api.modrinth.com/v2/project/knockdc/version/" + this.value));
});

document.getElementById("copy").addEventListener("click", function () {
     navigator.clipboard.writeText(document.getElementById("config").innerText);
});