start cmd.exe @cmd /k "cd polyevent/event&mvn tomee:run"
start cmd.exe @cmd /k "cd dotNet&mono server.exe"
start cmd.exe @cmd /k "cd client&mvn exec:java"