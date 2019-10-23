$(document).ready(function () {
    const clientSecret = "H3ZKfpHvuG";
    const pageUrl = new URL($(location).attr("href"));
    const urlParams = new URLSearchParams(pageUrl.search);
    const code = urlParams.get("code");

    console.log(`pageUrl: ${pageUrl}`);
    console.log(`urlParams: ${urlParams}`);
    console.log(`code: ${code}`);

    if (code) {
        $(":input[id=\"accessTokenBtn\"]").prop('disabled', false);
        $(":input[id=\"refreshTokenBtn\"]").prop('disabled', false);
    }

    $("#accessTokenBtn").on("click", function (e) {
        e.preventDefault();

        $("#accessTokenBtn")
            .html("<span class=\"spinner-border spinner-border-sm\" role=\"status\"></span> Retrieving...");

        $
            .ajax({
                url: "https://api.infusionsoft.com/token",
                data: {
                    client_id: $("#inputClientId").val(),
                    client_secret: clientSecret,
                    code: code,
                    grant_type: "authorization_code",
                    redirect_uri: "http://localhost:8080/index.html"
                },
                method: "POST"
            })
            .then(function (response) {
                $("#accessTokenBtn").text("Access Token");
                $("#inputAccessToken").val(response.access_token);
                $("#inputRefreshToken").val(response.refresh_token);
            });
    });

    $("#refreshTokenBtn").on("click", function (e) {
        e.preventDefault();

        $("#refreshTokenBtn")
            .html("<span class=\"spinner-border spinner-border-sm\" role=\"status\"></span> Retrieving...");

        $
            .ajax({
                url: "https://cors-anywhere.herokuapp.com/https://api.infusionsoft.com/token",
                headers: {
                    "Authorization": `Basic ${btoa(`${$("#inputClientId").val()}:${clientSecret}`)}`,
                    "Content-Type": `application/x-www-form-urlencoded`
                },
                data: {
                    grant_type: "refresh_token",
                    refresh_token: $("#inputRefreshToken").val()
                },
                method: "POST"
            })
            .then(function (response) {
                $("#refreshTokenBtn").text("Refresh Token");
                $("#inputAccessToken").val(response.access_token);
                $("#inputRefreshToken").val(response.refresh_token);
            });
    });
});