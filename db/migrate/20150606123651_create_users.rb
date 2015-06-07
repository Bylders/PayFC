class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.string :name
      t.string :pass
      t.string :aid
      t.integer :money

      t.timestamps null: false
    end
  end
end
