json.array!(@auth1s) do |auth1|
  json.extract! auth1, :id, :aadharid, :pincode, :misc, :misc2
  json.url auth1_url(auth1, format: :json)
end
