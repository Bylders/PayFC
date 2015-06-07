class Auth1sController < ApplicationController
  before_action :set_auth1, only: [:show, :edit, :update, :destroy]

  # GET /auth1s
  # GET /auth1s.json
  def index
    @auth1s = Auth1.all
  end

  # GET /auth1s/1
  # GET /auth1s/1.json
  def show
  end

  # GET /auth1s/new
  def new
    @auth1 = Auth1.new
  end

  # GET /auth1s/1/edit
  def edit
  end

  # POST /auth1s
  # POST /auth1s.json
  def create
    @auth1 = Auth1.new(auth1_params)
    @result = HTTParty.post("https://ac.khoslalabs.com/hackgate/hackathon/otp/", 
      :body => { 'aadhaar-id' => @auth1.aadharid.to_str, 
       'location' => { 'type' => 'pincode' ,' pincode' => @auth1.pincode.to_str},
       'channel' => 'SMS'
       }.to_json,
       :headers => { 'Content-Type' => 'application/json' }
       )

    render :json => @result.to_json
    # respond_to do |format|
    #   if @auth1.save
    #     format.html { redirect_to @auth1, notice: 'Auth1 was successfully created.' }
    #     format.json { render :show, status: :created, location: @auth1 }
    #   else
    #     format.html { render :new }
    #     format.json { render json: @auth1.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # PATCH/PUT /auth1s/1
  # PATCH/PUT /auth1s/1.json
  def update
    respond_to do |format|
      if @auth1.update(auth1_params)
        format.html { redirect_to @auth1, notice: 'Auth1 was successfully updated.' }
        format.json { render :show, status: :ok, location: @auth1 }
      else
        format.html { render :edit }
        format.json { render json: @auth1.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /auth1s/1
  # DELETE /auth1s/1.json
  def destroy
    @auth1.destroy
    respond_to do |format|
      format.html { redirect_to auth1s_url, notice: 'Auth1 was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_auth1
      @auth1 = Auth1.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def auth1_params
      params.require(:auth1).permit(:aadharid, :pincode, :misc, :misc2)
    end
end
