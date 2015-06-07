class CreateAuth2s < ActiveRecord::Migration
  def change
    create_table :auth2s do |t|
      t.string :otp

      t.timestamps null: false
    end
  end
end
