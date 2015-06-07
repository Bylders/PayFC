class Auth2sController < ApplicationController
  before_action :set_auth2, only: [:show, :edit, :update, :destroy]

  # GET /auth2s
  # GET /auth2s.json
  def index
    @auth2s = Auth2.all
  end

  # GET /auth2s/1
  # GET /auth2s/1.json
  def show
  end

  # GET /auth2s/new
  def new
    @auth2 = Auth2.new
  end

  # GET /auth2s/1/edit
  def edit
  end

  # POST /auth2s
  # POST /auth2s.json
  def create
    @auth2 = Auth2.new(auth2_params)
    @result2 = HTTParty.post("https://ac.khoslalabs.com/hackgate/hackathon/otp/", 
      :body => { 'aadhaar-id' => session[:aadharid].to_str, 
       'location' => { 'type' => 'pincode' ,' pincode' => session[:pincode].to_str},
       'modality' => 'otp',
       'otp' => @auth2.otp,
       'device-id' => 'public',
       'certificate-type' => 'prepod'
       }.to_json,
       :headers => { 'Content-Type' => 'application/json' }
       )
    render :json => @result2.to_json
    # respond_to do |format|
    #   if @auth2.save
    #     format.html { redirect_to @auth2, notice: 'Auth2 was successfully created.' }
    #     format.json { render :show, status: :created, location: @auth2 }
    #   else
    #     format.html { render :new }
    #     format.json { render json: @auth2.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # PATCH/PUT /auth2s/1
  # PATCH/PUT /auth2s/1.json
  def update
    respond_to do |format|
      if @auth2.update(auth2_params)
        format.html { redirect_to @auth2, notice: 'Auth2 was successfully updated.' }
        format.json { render :show, status: :ok, location: @auth2 }
      else
        format.html { render :edit }
        format.json { render json: @auth2.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /auth2s/1
  # DELETE /auth2s/1.json
  def destroy
    @auth2.destroy
    respond_to do |format|
      format.html { redirect_to auth2s_url, notice: 'Auth2 was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_auth2
      @auth2 = Auth2.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def auth2_params
      params.require(:auth2).permit(:otp)
    end
end
