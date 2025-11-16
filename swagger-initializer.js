window.onload = function() {
  // Begin Swagger UI call region
  const ui = SwaggerUIBundle({
    url: "openapi.json", // 關鍵：指向您上傳的 openapi.json
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout"
  });
  // End Swagger UI call region
  window.ui = ui;
};