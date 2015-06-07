require 'test_helper'

class Auth2sControllerTest < ActionController::TestCase
  setup do
    @auth2 = auth2s(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:auth2s)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create auth2" do
    assert_difference('Auth2.count') do
      post :create, auth2: { otp: @auth2.otp }
    end

    assert_redirected_to auth2_path(assigns(:auth2))
  end

  test "should show auth2" do
    get :show, id: @auth2
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @auth2
    assert_response :success
  end

  test "should update auth2" do
    patch :update, id: @auth2, auth2: { otp: @auth2.otp }
    assert_redirected_to auth2_path(assigns(:auth2))
  end

  test "should destroy auth2" do
    assert_difference('Auth2.count', -1) do
      delete :destroy, id: @auth2
    end

    assert_redirected_to auth2s_path
  end
end
