json.array!(@auth2s) do |auth2|
  json.extract! auth2, :id, :otp
  json.url auth2_url(auth2, format: :json)
end
