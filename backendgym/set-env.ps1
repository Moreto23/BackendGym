# =======================
# VARIABLES MERCADO PAGO
# =======================
$env:MP_ACCESS_TOKEN="APP_USR-475422353683123-101600-b81b50447ed9391c010c75ca7f935867-2929070190"
$env:MP_BACK_SUCCESS="https://adventurous-juniper-gyromagnetic.ngrok-free.dev/api/pagos/mercadopago/return"
$env:MP_BACK_FAILURE="https://adventurous-juniper-gyromagnetic.ngrok-free.dev/api/pagos/mercadopago/return"
$env:MP_BACK_PENDING="https://adventurous-juniper-gyromagnetic.ngrok-free.dev/api/pagos/mercadopago/return"
$env:MP_NOTIFICATION_URL="https://adventurous-juniper-gyromagnetic.ngrok-free.dev/api/pagos/mercadopago/webhook"

# =======================
# VARIABLES BASE DE DATOS
# =======================
$env:DB_URL="jdbc:mysql://centerbeam.proxy.rlwy.net:22649/railway?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
$env:DB_USER="root"
$env:DB_PASSWORD="ZTrVBCOfsqQbepbVAnoFhPZmkQCjlGDW"

# =======================
# JWT SECRET
# =======================
$env:JWT_SECRET="F93A1C7E9B204A51C9F7D3A8E4B6F12A7D9C3F4A1B6D8E2F9C3A7E1B5D9F42C7"

# =======================
# VARIABLES CORREO
# =======================
$env:MAIL_USER="sebaxtian.lco@gmail.com"
$env:MAIL_PASS="ajgaxkrouswilxaj"

Write-Host "✅ Variables de entorno cargadas para AngelsGym"
