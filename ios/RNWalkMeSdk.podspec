
Pod::Spec.new do |s|
  s.name         = "RNWalkMeSdk"
  s.version      = "2.0.0"
  s.summary      = "RNWalkMeSdk"
  s.description  = <<-DESC
                  RNWalkMeSdk
                   DESC
  s.homepage     = "http://walkme.com"
  s.license      = "Commercial"
  s.author       = { "WalkMe Inc" => "http://walkme.com" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => "https://github.com/abbiio/react-native-walkme-sdk.git", :tag => "master" }
  s.source_files  = "RNWalkMeSdk/**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React"
  s.dependency 'abbi'

end
