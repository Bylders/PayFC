require 'test_helper'

class Auth1sControllerTest < ActionController::TestCase
  setup do
    @auth1 = auth1s(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:auth1s)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create auth1" do
    assert_difference('Auth1.count') do
      post :create, auth1: { aadharid: @auth1.aadharid, misc2: @auth1.misc2, misc: @auth1.misc, pincode: @auth1.pincode }
    end

    assert_redirected_to auth1_path(assigns(:auth1))
  end

  test "should show auth1" do
    get :show, id: @auth1
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @auth1
    assert_response :success
  end

  test "should update auth1" do
    patch :update, id: @auth1, auth1: { aadharid: @auth1.aadharid, misc2: @auth1.misc2, misc: @auth1.misc, pincode: @auth1.pincode }
    assert_redirected_to auth1_path(assigns(:auth1))
  end

  test "should destroy auth1" do
    assert_difference('Auth1.count', -1) do
      delete :destroy, id: @auth1
    end

    assert_redirected_to auth1s_path
  end
end
