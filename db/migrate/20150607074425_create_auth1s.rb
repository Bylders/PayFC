class CreateAuth1s < ActiveRecord::Migration
  def change
    create_table :auth1s do |t|
      t.string :aadharid
      t.string :pincode
      t.integer :misc
      t.string :misc2

      t.timestamps null: false
    end
  end
end
