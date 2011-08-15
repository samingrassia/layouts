<layouts>
	<layout id="Operator">
		<window id="keyboard_operator_window" name="Operator" background-color="3B3D45" background-color-alpha="255" left="0" top="550" width="1440" height="825" resizable="false" border="true" server="" scroll="false">
			
			<flowlayout id="flow_layout" margin-x="15" margin-y="15" gap-x="5" gap-y="5" left="260" top="550" width="1440" height="975">
				
				<scrollview id="knob_1_container" left="25" top="10" width="1410" height="195">
					<statictext id="knob_1_label" left="5" top="0" width="50" height="35" string="Sample"/>
					
					<knob id="knob_1" left="5" top="40" width="50" height="70"/>
					<statictext id="knob_1_label" left="12" top="75" width="50" height="35" string="Trans."/>
	
					<knob id="knob_2" left="65" top="40" width="50" height="70"/>
					<statictext id="knob_2_label" left="72" top="75" width="50" height="35" string="Stretch"/>

					<knob id="knob_3" left="130" top="40" width="50" height="70"/>
					<statictext id="knob_3_label" left="135" top="75" width="50" height="35" string="Position"/>
					
					<knob id="knob_4" left="195" top="40" width="50" height="70"/>
					<statictext id="knob_4_label" left="205" top="75" width="50" height="35" string="Width"/>
					
					<soundfileview id="this_file_view" left="925" top="10" width="430" height="175" />
					
					<button id="button_play" left="5" top="120" width="50" height="25">
						<state label=">" text-color="FFFFFF" text-color-alpha="255" background-color="3B3D45" background-color-alpha="255" />
						<state label="| |" text-color="000000" text-color-alpha="255" background-color="FF9900" background-color-alpha="255" />
					</button>
					
					<button id="button_load" left="5" top="150" width="50" height="25">
						<state label="LOAD" text-color="FFFFFF" text-color-alpha="255" background-color="3B3D45" background-color-alpha="255" />
					</button>
					
					<button id="button_select_all" left="60" top="120" width="50" height="25">
						<state label="Sel. All" text-color="000000" text-color-alpha="255" background-color="FF9900" background-color-alpha="255" />
					</button>
				</scrollview>
				
				<scrollview id="container_assign" left="25" top="10" width="75" height="195">
					<statictext id="container_assign_label" left="15" top="0" width="50" height="35" string="Interface"/>
					
					<button id="button_midi_assign" left="12" top="40" width="50" height="25">
						<state label="MIDI" text-color="FFFFFF" text-color-alpha="255" background-color="3B3D45" background-color-alpha="255" />
						<state label="MIDI" text-color="FFFFFF" text-color-alpha="255" background-color="FF9900" background-color-alpha="255" />
					</button>
					
					<button id="button_key_assign" left="12" top="70" width="50" height="25">
						<state label="KEY" text-color="FFFFFF" text-color-alpha="255" background-color="3B3D45" background-color-alpha="255" />
						<state label="KEY" text-color="FFFFFF" text-color-alpha="255" background-color="FF9900" background-color-alpha="255" />
					</button>
					
					<button id="button_assign_reset" left="12" top="100" width="50" height="25">
						<state label="RESET" text-color="FFFFFF" text-color-alpha="255" background-color="3B3D45" background-color-alpha="255" />
					</button>
					<button id="button_screen" left="12" top="160" width="50" height="25">
						<state label="FULL" text-color="000000" text-color-alpha="255" background-color="00FF00" background-color-alpha="255" />
						<state label="NORM" text-color="000000" text-color-alpha="255" background-color="FF9900" background-color-alpha="255" />
					</button>
				</scrollview>
				
				<scrollview id="balance2_container" left="25" top="10" width="75" height="195">
					<statictext id="lable" left="5" top="0" width="55" height="35" string="Balance2"/>
					
					<knob id="knob_balance2_pan" left="10" top="40" width="50" height="70"/>
					<statictext id="label" left="20" top="75" width="40" height="35" string="L     R"/>
					
					<knob id="knob_balance2_vol" left="10" top="115" width="50" height="70"/>
					<statictext id="label" left="25" top="150" width="50" height="35" string="Vol."/>
				</scrollview>
				
				<scrollview id="knob_1_container" left="25" top="10" width="75" height="195">
					<statictext id="knob_1_label" left="10" top="0" width="50" height="35" string="Sound In"/>
					<knob id="knob_sound_in_vol" left="15" top="40" width="40" height="70"/>
					<statictext id="knob_sound_in_vol_label" left="25" top="70" width="50" height="35" string="Vol."/>
				</scrollview>
				
				<scrollview id="reverb_container" left="25" top="10" width="150" height="195">
					<statictext id="label" left="10" top="0" width="50" height="35" string="Freeverb"/>
					
					<knob id="knob_freeverb_mix" left="15" top="40" width="50" height="70"/>
					<statictext id="label" left="30" top="75" width="40" height="35" string="Mix"/>
	
					<knob id="knob_freeverb_room" left="15" top="115" width="50" height="70"/>
					<statictext id="label" left="25" top="150" width="50" height="35" string="Room"/>

					<knob id="knob_freeverb_damp" left="75" top="40" width="50" height="70"/>
					<statictext id="label" left="85" top="75" width="50" height="35" string="Damp"/>
					
					<knob id="knob_freeverb_vol" left="75" top="115" width="50" height="70"/>
					<statictext id="label" left="90" top="150" width="50" height="35" string="Vol."/>
				</scrollview>
				
				<scrollview id="pv_mag_freeze_container" left="25" top="10" width="75" height="195">
					<statictext id="knob_1_label" left="15" top="0" width="50" height="35" string="Freeze"/>
					<button id="button_pv_mag_freeze" left="11" top="40" width="50" height="25">
						<state label="ON" text-color="000000" text-color-alpha="255" background-color="00FF00" background-color-alpha="255" />
						<state label="OFF" text-color="000000" text-color-alpha="255" background-color="FF9900" background-color-alpha="255" />
					</button>
				</scrollview>
				
				<scrollview id="bufallpassl_container" left="25" top="10" width="150" height="195">
					<statictext id="knob_1_label" left="5" top="0" width="125" height="35" string="Delay - BufAllPassL"/>
					
					<knob id="bufallpassl_delay" left="15" top="40" width="50" height="70"/>
					<statictext id="label" left="25" top="75" width="40" height="35" string="Delay"/>
	
					<knob id="bufallpassl_decay" left="15" top="115" width="50" height="70"/>
					<statictext id="label" left="22" top="150" width="50" height="35" string="Decay"/>

					<knob id="bufallpassl_vol" left="75" top="40" width="50" height="70"/>
					<statictext id="label" left="90" top="75" width="50" height="35" string="Vol."/>
					
				</scrollview>
				
				<scrollview id="knob_1_container" left="25" top="10" width="255" height="195">
					<statictext id="knob_1_label" left="5" top="0" width="150" height="35" string="PitchShift"/>
				</scrollview>
				
				<scrollview id="knob_1_container" left="25" top="10" width="255" height="195">
					<statictext id="knob_1_label" left="5" top="0" width="150" height="35" string="FreqShift"/>
				</scrollview>
				
			</flowlayout>
		</window>
	</layout>
</layouts>